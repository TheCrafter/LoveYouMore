# LoveYouMore - v1.1
Date: 28/3/2015


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

All you have to do is place 10 sounds (if you want more you just have
to make a small adjustments to the code) with names:
msg1.ogg, msg2.ogg, ..., msg10.ogg in app\src\main\res\raw folder.


## Notes

1. You dont have to record in specific file format as long as you select
   a format that Android supports. Look it up on Android's documentation.
   Ogg format (as I've selected) is always a safe bet.

2. You can add/remove messages directly from msg_array.xml

## Changelog

### v1.1

- Minor design changes.
- Added support for text messages, not only audio messages.

## Contact me

Email: dimitrisvlh@gmail.com