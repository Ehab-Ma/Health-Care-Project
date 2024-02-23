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


![Screenshot 2024-02-23 225229](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/3164df6d-0357-4f79-a907-9b573c3a2eb6)
![Screenshot 2024-02-23 225254](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/ac13cd25-e179-4963-aac8-2118bfccd09a)
![Screenshot 2024-02-23 225315](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/0e3311b3-e377-4302-b231-8eb0dada1928)
![Screenshot 2024-02-23 225332](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/dab91040-625e-489d-a30d-525326d307c4)
![Screenshot 2024-02-23 225348](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/67b2c50d-8787-478b-b1bc-78897f70e294)
![Screenshot 2024-02-23 225422](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/2a1b2338-8764-4a45-b75a-e1e149c53fdc)
![Screenshot 2024-02-23 225443](https://github.com/Ehab-Ma/Health-Care-Project/assets/9238![Screenshot 2024-02-23 225927](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/5a7d9bbf-a013-4215-a2bd-9b3a42a035c2)
3051/d9e91008-dff7-425a-af6c-f6fe568c99a2)
![Screenshot 2024-02-23 ![Screenshot 2024-02-23 225840](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/4518428e-23eb-4d30-a25c-fd698c297789)
225729](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/02885375-f363-4626-9ca2-50d70dbd2539)
![Screenshot 2024-02-23 230010](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/cbbc4d8c-2424-4167-aee2-fd4b5a6088d3)
![Screenshot 2024-02-23 230237](https://github.com/Ehab-Ma/Health-Care-Project/assets/92383051/a4e0e763-069e-4cfd-a2e2-7c9458a5c851)
