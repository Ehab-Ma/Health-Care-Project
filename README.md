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

![Screenshot 2024-02-23 225229](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/c2dd8b76-ae80-4bcd-8028-4bd903186c17)
![Screenshot 2024-02-23 225254](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/4f84d659-4f1c-4361-82c2-dce5eafca283)
![Screenshot 2024-02-23 225315](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/e755741d-ed9c-4daf-b5a4-f89251c6091c)
![Screenshot 2024-02-23 225332](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/9d8fb3a0-0a5b-484d-822d-a0c9a8d6b3d2)
![Screenshot 2024-02-23 225348](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/fb693783-ddbd-45e9-81a4-a7deffdd514e)
![Screenshot 2024-02-23 225422](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/059ef3e5-0809-45a4-8da4-63a757c78254)
![Screenshot 2024-02-23 225443](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/e5a01850-ba36-44bc-9b00-7ecb4b201c8f)
![Screenshot 2024-02-23 225729](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/0e4a2337-f458-44e6-bf03-91cdffd11df7)
![Screenshot 2024-02-23 225840](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/924add34-a50c-473e-929d-1b9c28ef101d)
![Screenshot 2024-02-23 225927](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/a90beec8-2941-40cc-9b33-2910b49a51e8)
![Screenshot 2024-02-23 230010](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/def093c0-729c-497c-b563-85e59a5120af)
![Screenshot 2024-02-23 230237](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/3ee868fc-09eb-430d-8328-95457d098785)
