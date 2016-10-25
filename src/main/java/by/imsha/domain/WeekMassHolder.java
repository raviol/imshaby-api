package by.imsha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.util.*;

/**
 * @author Alena Misan
 */
public class WeekMassHolder {

    @JsonIgnore
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private LocalDate currentDate;

    public WeekMassHolder(LocalDate currentDate){
        this.currentDate = currentDate;
        weekMasses = new HashMap<WeekDayTimeKey, List<Mass>>();
        massesByDay = new HashMap<DayOfWeek, Map<LocalTime, List<Mass>>>();
    }
    @JsonIgnore
    private Map<WeekDayTimeKey, List<Mass>> weekMasses;


    private Map<DayOfWeek, Map<LocalTime, List<Mass>>> massesByDay;

    public Map<DayOfWeek, Map<LocalTime, List<Mass>>> getMassesByDay() {
        return massesByDay;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public Map<WeekDayTimeKey, List<Mass>> getWeekMasses() {
        return weekMasses;
    }

    public WeekMassHolder build(List<Mass> masses){

        LocalDate startDate = getCurrentDate().minusDays(getCurrentDate().getDayOfWeek().getValue() - 1);
        LocalDate nextWeekSunday = startDate.plusDays(7);
        for (Mass mass : masses) {
            long singleStartTimestamp = mass.getSingleStartTimestamp();
            LocalDateTime singleStartTime =     LocalDateTime.ofInstant(Instant.ofEpochMilli(singleStartTimestamp), ZoneId.systemDefault());

            if(singleStartTime != null){
                 if(mass.getTime() != null){
                     log.error(String.format("Mass with ID: %s is configured incorrectly - time and singleStartTime cannot be not null at the same time!", mass.getId()));
                     continue;
                 }
                 LocalDate singleStartDate = singleStartTime.toLocalDate();

                 if(singleStartDate.isAfter(startDate.minusDays(1)) && singleStartDate.isBefore(nextWeekSunday)){
                     populateContainers(mass, singleStartTime.getDayOfWeek(), singleStartTime.toLocalTime());
                 }
            }else{
                 if(mass.getTime() == null){
                     log.error(String.format("Mass with ID: %s is configured incorrectly - time and singleStartTime cannot be null at the same time!", mass.getId()));
                     continue;
                 }
                 int[] days = mass.getDays();
                for (int day : days) {
                    populateContainers(mass, DayOfWeek.of(day), LocalTime.parse(mass.getTime()));
                }
            }
        }
        return this;
    }

    private void populateContainers(Mass mass, DayOfWeek dayOfWeek, LocalTime time) {
        addToWeekMasses(mass, dayOfWeek, time);
        addToMassesByDay(mass,  dayOfWeek, time);
    }

    private void addToWeekMasses(Mass mass, DayOfWeek dayOfWeek, LocalTime time){
        WeekDayTimeKey weekTimeKey = new WeekDayTimeKey(dayOfWeek, time);
        weekMasses.computeIfAbsent(weekTimeKey, v -> new ArrayList<Mass>()).add(mass);
    }

    private void addToMassesByDay(Mass mass,  DayOfWeek dayOfWeek, LocalTime time){
        log.info(dayOfWeek.toString());
        massesByDay.computeIfAbsent(dayOfWeek, v -> new HashMap<LocalTime, List<Mass>>()).
                computeIfAbsent(time, v -> new ArrayList<Mass>()).add(mass);
    }

    class WeekDayTimeKey {
        private DayOfWeek weekDay;
        private LocalTime time;

        WeekDayTimeKey(DayOfWeek weekDay, LocalTime time) {
            this.weekDay = weekDay;
            this.time = time;
        }

        DayOfWeek getWeekDay() {
            return weekDay;
        }

        void setWeekDay(DayOfWeek weekDay) {
            this.weekDay = weekDay;
        }

        LocalTime getTime() {
            return time;
        }

        void setTime(LocalTime time) {
            this.time = time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof WeekDayTimeKey)) return false;

            WeekDayTimeKey that = (WeekDayTimeKey) o;

            if (weekDay != that.weekDay) return false;
            if (!time.equals(that.time)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = weekDay.getValue();
            result = 31 * result + time.hashCode();
            return result;
        }

    }

}
