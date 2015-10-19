Name: Jue Chemparathy
SJSU Id: 009343503
Email Id: jue.chemparathy@sjsu.edu



Name: Aastha Bhatia
SJSU Id: XXXX
Email Id:  XXXXX@sjsu.edu

Process to Build app
1. Go to Build option
2. Click on Generate Signed apk
3. Create key store if not present.
4. Add key for your application
5. Click on Generate signed apk.
6. Apk file will be created in app folder.
7. Install apk on your device and run the application.


Steps:
1. Click on SignIn option.
2. There are two ways to login.
	i. Put username and password and click on login.
	ii. Use saved user credentials.
3. Accept all agreements.
4. Will take few moments to load the data.
5. There will be 2 tabs
	i. Playlist
	ii. Search
6. Playlist will show CMPE-277 playlist we maintain for the app user.
7. Search will let user to enter keyword and display results.
8. On selecting any video line item,video player is triggered to playback the video in fullscreen.
9. A NavigationDrawer is provided with shortcuts to Search,Playlist and to Logout.


Intr
CMPE 277
Lab 2 - GooglePlusMini

Last updated: 02/23/2015

Introduction
This is a team project, with maximum 2 people allowed for each team. Your team is going to implement an application called GooglePlusMini (GPM), which allows a user to login with a Google Account (with GooglePlus enabled) to view his/her profile, circles, and friends by integrating with the GooglePlus (G+) APIs.

Requirements
1.	SDK
a.	kitkat4.4 (API Level: 19)
b.	Make sure the UI renders properly on the NEXUS 5 simulator
2.	Integration of G+ API
a.	Authenticating against G+ API through Oauth2 authorization
i.	https://developers.google.com/+/api/oauth
ii.	https://developers.google.com/+/mobile/android/getting-started
b.	Accessing profile endpoint to retrieve logged user’s G+ profile
i.	https://developers.google.com/+/domains/api/people
c.	Accessing circles endpoint to retrieve circle information
i.	https://developers.google.com/+/domains/api/circles
d.	Accessing friends endpoint to retrieve friend information
i.	https://developers.google.com/+/domains/api/people
3.	App Navigation and Views
a.	Login View This view should be shown before user login, it can be as simple as a Login button that redirects user to Oauth2 module for authentication. Alternatively, you can use the first Google account that has already signed into the device through other Google services, such as GMail.
b.	User Profile View: This is should to be shown after a user Logged In. It renders the following information retrieved from user’s G+ Account: Profile picture, Name, Occupation, Organization and aboutMe.
c.	Circles View: A View for displaying a list of circles with circle name
i.	Friends View: This view will be shown when the user selects on a specific circle. It should render a list of friends within this circle along with their profile pictures and names.
1.	Friend Profile View: This view should look similar to the User Profile View but with an extra email icon on the top-left of the screen that will fire up an implicit intent to the built-in email client for sending an email to this friend. Friend’s email address should be automatically populated in the receiver/recipient field
d.	Navigation The app has two top level views: User Profile View and Circle View. You need to provide a way to switch between the two views, through either tabs or a navigation drawer. You also need to allow users to navigate through Circles, Friends, and Friend Profile views.

Grading
Feature correctness, user interface, and documentation are worth 3.5, 1, and 0.5 points respectively
Submission
Submit 1 zip file and an APK file separately through Canvas:
1.	Only ONE of the team members can submit to Canvas. You will get a penalty if more than one team member submits.
2.	A zip file contains “README.txt and Screenshots”
a.	README.txt
i.	The name, student ID, and email ID of EACH group member
ii.	Instruction on how to build your app
iii.	Introduction of your app and a brief user guide on how to use it
b.	Screenshots for each view
c.	All the source code, resources, and project files, with their directory structures preserved. You may lose points if we cannot build your app. Please do NOT submit compiled .dex or .class files.
3.	Signed APK file (Android Studio->Run->Generate Signed APK)
a.	Filename: GooglePlusMini.apk
b.	App name: GooglePlusMini (The actual app name shown on the Device/Emulator)
