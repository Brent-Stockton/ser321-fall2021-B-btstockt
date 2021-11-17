# Assignment 4 Activity 1
## Description
Activity 1:

Youtube Video Link: https://www.youtube.com/watch?v=y6mOK7Q6Hig

Task 1: Make Performer more interesting

Running Server: gradle runServer -Pport=8000 -q --console=plain Running Client: gradle runClient -Phost=localhost -Pport=8000 -q --console=plain

I was able to make the performer more itneresting by implementing the add , remove , display and count. While count does give correct output it does throw an exception. I was unable to implement the reverse although I did attempt it and left my code.

Task 2: Make the server multi-threaded

Running Server: gradle runThreadedServer -Pport=8000 -q --console=plain Running Client: gradle runClient -Phost=localhost -Pport=8000 -q --console=plain

I was able to implement the class ThreadedServer that allows unbounded incoming connections from the server with no clients blocking.

Task 3: Make the multi-threaded server bounded

Running Server: gradle runThreadedPoolServer -Pport=8000 -Pnthread=2 -q --console=plain Running Client: gradle runClient -Phost=localhost -Pport=8000 -q --console=plain

I was able to implement a class ThreadedPoolServer. While it was bounded before I may either be inputing my gradle call or something may have accidentally changed but I did not have time to identify what was missing. When I checked the bound was no longer working unless I am doing something wrong. You will have to examine the code. After making the server multithreaded, I adjusted the code to in Main to run an Executor object that created a newFixedThreadpool(nthreads). After, that object exected serverWorker.

## Protocol

### Requests
request: { "selected": <int: 1=add, 2=remove, 3=display, 4=count, 5=reverse,
0=quit>, "data": <thing to send>}

  add: data <string>
  remove: data <int>
  display: no data
  count: no data
  reverse: data <int>
  quit: no data

### Responses

sucess response: {"type": <"add",
"remove", "display", "count", "reverse", "quit"> "data": <thing to return> }

type <String>: echoes original selected from request (e.g. if request was "add", type will be "add")
data <string>: add = new list, remove = removed element, display = current list, count = num elements, reverse = new list


error response: {"type": "error", "message"": <error string> }
Should give good error message if something goes wrong which explains what went wrong


## How to run the program
### Terminal
Base Code, please use the following commands:
```
  Running Server Task 1: gradle runServer -Pport=8000 -q --console=plain Running Client: gradle runClient -Phost=localhost -Pport=8000 -q --console=plain
  
  Running Server Task 2: gradle runThreadedServer -Pport=8000 -q --console=plain Running Client: gradle runClient -Phost=localhost -Pport=8000 -q --console=plain
  
  Running Server Task 3: gradle runThreadedPoolServer -Pport=8000 -Pnthread=2 -q --console=plain Running Client: gradle runClient -Phost=localhost -Pport=8000 -q --console=plain



```
```   
    For Client, run "gradle runClient -Phost=localhost -Pport=9099 -q --console=plain"
```   



