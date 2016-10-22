package by.imsha.api.rest.docs;

import org.jsondoc.core.annotation.global.ApiGlobal;
import org.jsondoc.core.annotation.global.ApiGlobalSection;
import org.jsondoc.core.annotation.global.ApiMigrationSet;

/**
 * @author Alena Misan
 */
@ApiGlobal(
        sections = {
                @ApiGlobalSection(
                        title = "API",
                        paragraphs = {
                                "This is to demonstrate the use of API provided"
                        }
                ),
                @ApiGlobalSection(
                        title = "Objects",
                        paragraphs = { "This is to demonstrate the use of Objects in system" }
                )
        }
)



public class  DocumentationConstants {
    public static final String MASS_CREATE = "MASS_SAVE";
    public static final String MASS_FIND_ONE = "MASS_FIND_ONE";
    public static final String MASS_DELETE = "MASS_DELETE";
    public static final String MASS_UPDATE = "MASS_UPDATE";


}
