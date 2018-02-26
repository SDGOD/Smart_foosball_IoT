Our Effective team passionately loves foosball, mobile technology, and IoT. And it was a question of time, when this project will be born. Smart Foosball IoT is inspired by Smart Foosball Handsome project, but has another approach and implementation. That's why it can't be named "smart foosball v2".

An article with the video about the previous project could be found here.

The whole project consists of four parts which depend on each other:

Firebase is used as a backend to collect information about matches and players.
Android part is for routing players and goal counting. Android UI is a simple and effective way to display information about all players and their statistic. Touch gestures allow choosing players conveniently for the current game.
Hardware part is for fixing goals. This part is based on Arduino Uno-like board and has been implemented on C++.
Slack bot is for looking for players and reporting the results. Slack is a common way of communication in our team and it's really simple to use slack bot and application for such type of purposes.
Firebase and Android are required, hardware part and slack bot are optional.
