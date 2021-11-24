# Assignment 4 Activity 1
## Description
The initail Performer code only has one function for adding strings to an array: 

## Protocol

### Requests
request: { "selected": <int: 1=add, 2=display, 0=quit>, "data": <thing to send>}

  add: data <string>
  display: no data
  quit: no data

### Responses

sucess response: {"type": <"add",
"display", "quit"> "data": <thing to return> }

type <String>: echoes original selected from request (e.g. if request was "add", type will be "add")
data <string>: add = new list, display = current list


error response: {"type": "error", "message"": <error string> }
Should give good error message if something goes wrong which explains what went wrong


## How to run the program
### Terminal
Base Code, please use the following commands:
```
  For Server, run "gradle runThreadedServer -Pport=8000 -q --console=plain" //TC connected to server
```
  ```
  For Nodes, run "gradle Node1 --console=plain, gradle Node2  --console=plain
  ``````   
    For Client, run "gradle Client -Phost=localhost -Pport=8000 -q --console=plain"
```   



