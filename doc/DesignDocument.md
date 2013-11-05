Design Document
===============

## Purpose of Project

We are developing an application to assist bird watchers and other people interested in birds. "Bird Buddy" displays the migratory patterns of several different birds as well as information about these birds. This application will also expose the developers to Android app development as well as test driven development.

Please see the attached [word document](BirdBuddyBirds.docx) for the data sources we will be using.

## UI Flowchart

Below is a diagram showing the different components of the application. To view a working demo of the application, visit the [fluidUI demo](https://www.fluidui.com/editor/live/preview/p_FyTNToGobErf9M3q2ElTvOFabkCXOkQt.1383625832629).

![UI Flowchart](img/ui-flowchart.png)

## High Level Entities

### Serialization Service
This service handles loading/saving entities from XML or JSON files. Please see the [example XML file](data.xml) for an example of how data will be stored.

### Bird Entity Service
The Bird Service entity handles the birds being used by the application.

### Bird Map Service
The Bird Map Service entity works on plotting the bird's migration paths onto a given Map.

![High Level Design](img/high-level-diagram.png)

## Low Level Design

### Serialization Service
This service handles loading/saving entities from XML or JSON files. 

### Bird Entities

#### Bird Class
The bird class keeps track of a bird's picture, description, and path locations.

#### Location Class
A location that keeps track of latitude, longitude, time, and path index. The path index will be used for linking locations in the correct order.

### Bird Map Service Classes

#### Bird Path Drawer
Takes in a single bird and draws their migratory path on the specified map.

## Benefits, Assumptions, and Risks

### Benefits

Nobody on our team has ever developed for Android before, so this will be a great learning project for us. We have aimed to keep the scope of the project manageable so that we can learn the basics of Android development without getting too concerned with creating a lot of content.

We assume that the user will have an Android 4.x phone or the ability to emulate it.

There are no significant risks to the user of this application. At worst, the user's phone may crash. Data services should not be used.
