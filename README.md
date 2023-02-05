
# Muzz App Challenge

I've taken on the challenge from Muzz where I need to create a small chat app between 2 users.
I've decided to use Jetpack Compose for the UI because it gives me a way to create custom UI components quite quickly and it's also the latest technology recommended by Google.



## Author

- [@dm4rco](https://github.com/dm4rco)


## Features

- Message List and Text Entry Box
- Message List items
    - Distinguish between received and sent messages by aligning them left or right.
    - The messages should be encased in a “bubble” as per Screenshot 1.
    - Item sectioning
- Observables
    - Used "mutableStateListOf<Message>()" inside of the MuzzChatViewModel which is being observed inside of the View
- Two way messages
    - Added some random messages that appear as a reply after sending messages. If a message appears, it will be random so multiple test cases can be covered


## Optimizations

- Tail logic
- Persistent storage

These are the two things I was not able to implement in the timeframe of the challenge.

I would probably need to take some more time to implement a proper storage system. I assume I would be using "Room" inside of a repository.

For the logic of the "tail", I will need some more time to really think about it.


## Screenshots

![App Screenshot](./Projects/screenshots/screenshot1.png)
![App Screenshot](./Projects/screenshots/screenshot2.png)

