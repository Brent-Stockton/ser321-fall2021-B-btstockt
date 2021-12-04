GRPC Services and Registry
The following folder contains a Registry.jar which includes a Registering service where Nodes can register to allow clients to find them and use their implemented GRPC services.

PROJECT DESCRIPTION:
In the assignment I was able to implement three diffrent gRPC services. Two were partially given as choices and one I was able to design on my own.
I was able to implement the Calculation Service, Story Service, and self designed Covid-19 Self-Checker Service.

Here is a link to my code and demo of the following services: 

Calculation Service:
For this service I was able to design a menu for the client to first enter an arbitrary number into the console to represent the amount of numbers he would like 
to run various calculations on. Then the client is prompted to choose those actual numbers in the amount pre3viously chosen. For example, if the client chose 3 numbers he will the be prompted to pick those 3 numbers in consecutive order and they will be stored into an arraylist. After the client has chosen the number the client will be prompted with another menu asking the client which operations they would like to perform. 
They can choose from:
Add - will add all given numbers.
Subtract - will take the first number and subtract all the other numbers.
Multiply - will multiply all given numbers.
Divide - will use the first number and divide it by all other numbers.
Back to Main Menu

The client will be able to run each oth these operations on the number chosen. After, they can return to the main menu to pick a new service or repeat the calculation service.

Some more detailed explanations will follow and please also check the build.gradle file

Before starting do a "gradle generateProto".

gradle runRegistryServer
Will run the Registry node on localhost (arguments are possible see gradle). This node will run and allows nodes to register themselves.

The Server allows Protobuf, JSON and gRPC. We will only be using gRPC

gradle runNode
Will run a node with an Echo and Joke service. The node registers itself on the Registry. You can change the host and port the node runs on and this will register accordingly with the Registry

gradle runClientJava
Will run a client which will call the services from the node, it talks to the node directly not through the registry. At the end the client does some calls to the Registry to pull the services, this will be needed later.

gradle runDiscovery
Will create a couple of threads with each running a node with services in JSON and Protobuf. This is just an example and not needed for assignment 6.

gradle testProtobufRegistration
Registers the protobuf nodes from runDiscovery and do some calls.

gradle testJSONRegistration
Registers the json nodes from runDiscovery and do some calls.
