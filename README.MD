# Log Watcher

In this exercise, you'll start things off by writing a unit test for LogWatcher.java. You want to make sure that the
watcher sends out a notification mail when a new log message is available.


# Aufgabe 1
What makes writing that test hard to do?


# Aufgabe 2

Then, things start to get complicated as new requirements gradually come in.

* The subscribers get annoyed, because they're flooded with mails.
  Only alert them if an actual error occured. (done)
* New developers (Antonio Materazzo and Fritz Schnitzel) join the team and need to be notified, too. (done)
* Robert Glaser wants to be notified only, if the infamous exception code 42 occurs. (done)
* Robert Glaser notices, that he needs way more information when a 42 occurs. So if that happens, he
  wants to receive the trace output preceding the exception. (done)
* The developers have a group chat. During the working hours (8h - 17h) they'd like a chat bot to
  notify them of everything but debug output. (done)
* On weekends, there's always one developer on standby. The guys rotate the dev with log-duty.
  If more than 10 errors pile up, he needs to be notified via all available channels. (done)

  
[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/modernSE/kata-log-watcher-java)

