# Android application for Smart foosball IoT

![screen](androidapp.png)

The Android application helps to choose players for the current match, display score, and start the countdown before the round beginning. Also, it lets activate the counting down by voice using code phrase: "Let's go". Information about players is loaded from Firebase database, and then application saves match results into Firebase. So, this Firebase integration is necessary for our needs.

For adding Firebase to your application you have to go to [firebase console](https://console.firebase.google.com) and add the Android application on Firebase project setup page. After that, you will be able to download *google-service.json* file. This file should be placed into *AndroidApp/app* directory.

Also, you have to activate login in firebase by *email/password* and create a new user for your application. It could be done in [firebase console](https://console.firebase.google.com) in Authentication section. Android application will try to find login and password in file *AndroidApp/app/src/main/res/raw/data* (without extension).
You should create a file on this path and add two lines into the file: the first one with the login, the second one with the password, and the third line with link to firebase storage. You can find this link in [firebase console](https://console.firebase.google.com) in Storage section (example gs://cool-foosball-app.appspot.com). After that, your application will be able to get players information from firebase and push matches events there.

If you want to add countdown voice activation, you should get [Yandex API key](https://tech.yandex.com/speechkit/) and add this key on the third line in *AndroidApp/app/src/main/res/raw/data* file.
