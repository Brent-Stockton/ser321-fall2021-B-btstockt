PROJECT DESCRIPTION:

Here is a link to my code and video demo of the following services: https://www.youtube.com/watch?v=-1N0WnwNZlU

To run this program use:
gradle runNode --console=plain
gradle runClientJava --console=plain

This program uses a combination of "yes," "no," "quit," and integer inputs to work the program. Menu clearly explains.

In the assignment I was able to implement three diffrent gRPC services. Two were partially given as choices and one I was able to design on my own.
I was able to implement the Calculation Service, Story Service, and self designed Covid-19 Self-Checker Service. The program iniates with a main menu giving 4 options for the client to choose from.

Joke Service - Arbitrary because it was already completed and I left it in my menu. Basically when chosen the client is promted to enter the number of jokes they would like to recieve. They choose that number and recieve the chosen amount of jokes in the console.

Calculation Service:
For this service I was able to design a menu for the client to first enter an arbitrary number into the console to represent the amount of numbers the client would like to run various calculations on. Then the client is prompted to choose those actual numbers in the amount previously chosen. For example, if the client chose 3 numbers they will the be prompted to pick those 3 numbers in consecutive order and those numbers will be stored into an arraylist. After the client has chosen the number, the client will be prompted with another menu asking the client which operations they would like to perform. 
They can choose from:
Add - will add all given numbers.
Subtract - will take the first number and subtract all the other numbers.
Multiply - will multiply all given numbers.
Divide - will use the first number and divide it by all other numbers.
Back to Main Menu

The client will be able to run each oth these operations on the collective of numbers previously chosen. After, they can return to the main menu to pick a new service or repeat the calculation service steps with new values.

Story Service:
The story service saves a story that the client creates sentence by sentence and keeps track of that story. The client, after choosing the story service menu option, is met with a single sentence story to add to. I decided to add an initial sentence to inspire the user once they have chose the service. After adding a sentence, they are then prompted to either add another sentence by typing "yes," or choosing "no" to exit and return to the main menu. The sentences are stored in an arraylist to keep track of the story and displayed each time a sentence is added.

Covid-19 Self Checker Service:
This service provides the client with a list of CDC questions and asseses if you either need immediate medical attention due to your symptoms or can merely schedule and appointment with a primary care physician for further review. One this option is chosen the client is given and menu with 3 options provided. The first option is the self-check survey. This option will prompt you to receive 10 symptom checking questions that they can answer "yes" or "no" to. If they answer  five "yes" answers to any of the questions before the list is complete they will be prompted to seek immediate medical attention. If the client reaches the end of the question list without 5 "yes" answers they will be prompted to seek a primary care physician appointment for further review. The second option on the menu is to view a sample of common survey questions. After choosing this option the client will be promted to enter a desired number of survey questions less than the list and will be given that number of sample questions. They will then be given the option to do another query or return to the menu. The final choice is to review the entire list of survey questions. Once this option is chosen the client will be sent the list of question to review. They are then able to quit and return to the main menu.

GRPC Services and Registry
The following folder contains a Registry.jar which includes a Registering service where Nodes can register to allow clients to find them and use their implemented GRPC services.

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
