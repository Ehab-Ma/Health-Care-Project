# OCSF Mediator Example

## Structure
Pay attention to the three modules:
1. **client** - a simple client built using JavaFX and OCSF. We use EventBus (which implements the mediator pattern) in order to pass events between classes (in this case: between SimpleClient and PrimaryController.
2. **server** - a simple server built using OCSF.
3. **entities** - a shared module where all the entities of the project live.

## Running
1. Run Maven install **in the parent project**.
2. Run the OCSF file using : clean install package.
3. Run the server using the exec:java goal in the server module.
4. Run the client using the javafx:run goal in the client module.
5. Press the button and see what happens!
![FinalBakcground](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/fd44a68a-03c7-471e-b9d8-e37aac49251a)
![Screenshot 2024-02-23 225229](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/fd98b40f-789e-4b4d-8575-17ecdec7a982)

