Post Production Report
======================

## Data Acquisition

The data acquisition aspect of this project was fairly intimidating and, perhaps, the most time-consuming as there are many sources of data, however few are in formats that lend themselves to easily be integrated into a computer program. Such sources were primarily in the form of graphics, charts and maps. In order to use well vetted and scientifically obtained and reviewed data sources, the Cornell University Bird Lab was chosen. The data was in mostly map form. The way we took this and processed it into data points was by using ERSI’s ArcGIS software suite.

The process of translating everything over to ArcGIS was a lengthy, time-consuming job. The first step in the process was to geo-reference the original data files which were image files (Figure 1) at this point.

![Figure 1](img/Figure1.png)

Geo-referencing is the process of capturing data from an atypical data source, and aligning geographic data to a known coordinate system. The next step was to take the rectified images and create polygon features within ArcGIS - we did this in order to “project”. This enabled us to project spatial data with a coordinate system that could be properly input into the program.

![Figure 2](img/Figure2.png)

We then took the polygons and used that data to create ordered point features representing the perimeter of each set of data on the original image. By creating attribute fields, these point features contained data structures that included information such as (X, Y) coordinates species, season and object ID.

![Figure 3](img/Figure3.png)

![Figure 4](img/Figure4.png)

GIS then converted the X, Y coordinates into (Longitude, Latitude) locations using Universal Transverse Mercator (UTM). Then the GIS layers were exported as text files for each bird and season (Figure 5). These files house the data used to generate the polygons in our mapping portion of the application.

![Figure 5](img/Figure5.png)
  
All of the data used for our source and database construction was obtained from http://www.allaboutbirds.org. This website is sponsored by Cornell University and their Cornell Lab of Ortnithology. Each of our four birds were selected on the basis of their varying habitat and behavior. The selected species for our application being: the Peregrine Falcon, Snowy Egret, Canadian Goose and the Hooded Warbler. The data was taken from their migratory routes as shown on the website's range maps, and this data is scientifically obtained through tagging and observation. Then the outlying - or perimeter- data was extracted and ordered to create a polygon overlay for a map. This information was used to generate GPS coordinates by digitizing and creating feature classes based on the polygons provided in the range maps. In order to do this, ersi's ArcGIS geomapping software was used. The resulting data was then manipulated to be used in XML files as a database for google maps API to draw the polygons per bird and per season in our application. In this way, a total of over 300,000 data points was obtained to create our database for all birds in all seasons, although a smaller portion of these are in use to allow for more rapid mapping and transitions between seasonal polygons.

Initially all 300,000 data points were being used, but this created an over-population and would not function properly. The difficulty was now to sift through the data points and find only the most necessary ones in order to create a useable polygon. This proved tedious as it required the sorting of each data point to determine whether it had fewer related points than the other. Each of these was then marked as a perimeter point. In the end the 300,000 inital points were limited to, 100 per season per bird - still maintaining a reliable polygon for mapping. This allowed for smooth transitions between the polygons showing the seasonal migrations of each bird.

## Learning Android

A difficulty at first was learning the Android SDK. As none of the team had experience with Android, it required much trial-and-error before things began running smoothly. Part of the frustration of this stemmed from the SDK not working properly on all of the team's machines. Once the machines were functioning properly, the problem arose that the app project would not build on one of the machines. Eventually these issues were all resolved, and work commenced appropriately.

## Refactoring

One area involving refactoring was the support of multiple data sets as opposed to the initial set-up of only one. Along with this was the need to limit the amount of data being considered to only the perimeter of the polygons. At first, all of the points within the polygon were being mounted to the map. This required far too much processing. Once the data was limited, the transitions were smooth and rapid.

## Work Load Distribution

* Derek: 25%
* John: 25%
* Shane: 25%
* Travis: 25%
