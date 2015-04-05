# LoveYouMore - v1.1

Author: Vlachakis Dimitris

Date: 5/4/2015


                             __/^\__   
                           ./       \.
                          /    ___    \    THE
                         /   ./   \.   \    CRAFTER
                        /  ./       \.  \
                       /  / ___   ___ \  \
                      /  / |___|-|___| \  \
                     /  /               \  \
                     \  \               /  /
                      \  \/\         /\/  /
                       \    \       /    /
                        `\   \     /   /`
                          `\  \   /  /`
                            `\_\ /_/`     
                   /  /                   \  \
                  /   \                   /   \
                 |  _.\\                 //._  |
                 !!!!  \|               |/  !!!!
                 \\\\                       ////
                 _______________________________
                 | o o o o o ------ o o o o o o |
                 | o ~ ~ o ~~ o o o o o o o o ~~|
                 | o ~ ~ o o ~~ o o o o o o o ~~|
                 | o - - - - - - - - - - - - - ~|
                 |______________________________|

# Short description

This application is a gift from me to my wonderful girlfriend, Ana!

Purpose of LoveYouMore is to be an "interactive" gift card. You just
have to press the heart icon and your recorded message or sound will
start playing. Also you are able to write down some messages and after
changing from "music mode" to "text message mdoe", pressing the heart 
image will result in displaying randomly one of your messages.


## Build

To build the project without errors you have to add resources correctly.
I left in place the png icons for the application but I removed the sounds.

All you have to do is place 20 sounds (if you want more you just have
to make a small adjustments to the code) with names:
msg1.ogg, msg2.ogg, ..., msg20.ogg in app\src\main\res\raw folder.


## Notes

1. You dont have to record in specific file format as long as you select
   a format that Android supports. Look it up on Android's documentation.
   Ogg format (as I've selected) is always a safe bet.

2. You can add/remove messages directly from msg_array.xml


## Screenshots

![Red Heart](https://raw.githubusercontent.com/TheCrafter/LoveYouMore/v1.1-dev/docs/Screenshots/red_heart.png)
![Purple Heart](https://raw.githubusercontent.com/TheCrafter/LoveYouMore/v1.1-dev/docs/Screenshots/purple_heart.png)
![Purple Heart with Message](https://raw.githubusercontent.com/TheCrafter/LoveYouMore/v1.1-dev/docs/Screenshots/purple_heart_with_message.png)


## Changelog

### v1.1

- Minor design changes.
- Added support for text messages, not only audio messages.
- App now requires vibration privilege.

## Contact me

Email: dimitrisvlh@gmail.com