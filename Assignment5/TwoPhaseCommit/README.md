# Assignment 5 Activity 2 Two Phase Commit
## Description
Program displays a client menu to either add a tip for SER321, Display a list for Ser321 or quit. Depending on client choice the client will connect to a server that connects to a transaction coordinator that will ask two nodes for permission to complete the client request. If both nodes agree the request will be fulfilled and displayed to the client. If both or one node decides to abort the client request will not be fullfilled and the client will be notified. If the client decides to quit the socket will be closed but the server will remain running. Program handles multiple clients on athreaded server and they will be able to view a global list of tips from all clients.

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



