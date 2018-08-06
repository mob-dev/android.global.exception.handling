### What is this repository for? ###

* Implement global exception handling in Application

### How do I get set up? ###

* Implement Thread.UncaughtExceptionHandler interface
* Define method uncaughtException of Thread.UncaughtExceptionHandler interface
* Write the line Thread.setDefaultUncaughtExceptionHandler(this) in constructor level function or onCreate for fragment and activity.
* [Optional] Create activity/view class to show the exception.