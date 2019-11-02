#!/bin/sh

helm delete imshaby-api --purge
helm delete mongodb --purge
pkill kubectl
