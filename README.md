# DemoProject_1
Threads:
A thread is a single sequential flow of control within a program. 

###Multithreading
Multithreading is a model of program execution that allows for multiple threads to be created within a process, executing independently but concurrently sharing process resources. Depending on the hardware, threads can run fully parallel if they are distributed to their own CPU core.

###ExcecutorService
Creates a pool of multiple threads to multiple tasks and in it those tasks are distributed among threads of threadpool created by executorservice.

###Implemented multithreading:

1.Using Thread class
2.Using Runnable interface
3.Using Callable interface

###Difference between Runnable and Callable:
The main difference here is when Runnable interface is used for thread implementation on completion of task it doesn't return anything and run function is of type void, thus it can not be known if task execution was completely successfull or there was some error. In case of callable interface call function is of type value returned when task completes, thus it can be known to check successful execution of task.It also returns exception if any exception occurs during execution of task.