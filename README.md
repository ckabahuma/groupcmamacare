# MamaCare Mobile Application
Authors:
1) Catherine Kabahuma - 2016/HD07/2059U
2) Jonathan Mpango - 2016/HD07/2066U
3) Glorious Orishaba - 2016/HD07/2076U 

# MHI8101 - Advanced Programming
Lecturer: Eng.Binomugisha
College of Computing & Information Technology


PRACTICAL PROJECT I 

MOBILE PLATFORMS & ARCHITECTURES REPORT


Introduction:

This report is a documentation of the design and development of the MamaCare Mobile Application. It explains the architecture and design pattern; textual requirement specifications; the design and overview of the application; and lastly the test cases.

Architecture and Design Pattern/Framework:

The architecture of the MamaCare mobile application is interaction-oriented in the sense that ease of interaction with the application by the user is of greater importance than the complex nature of the underlying technology or coding. 
It has been built based on the Model View Controller (MVC) design pattern, as it’s the most suitable style for enabling display of multiple user views using the same database. 

Use Cases (Textual Requirements Specifications):

The use cases or textual requirements specification for the MamaCare mobile application include;

Data Entry for a New Case: 
1. CHW opens MamaCare App
2. Logs into his/her account using assigned username and password (access credentials)
3. Enters data of a new pregnant woman into Registration Form.
4. Saves the case (pregnant woman) data.

Updating a Case:
1. CHW opens MamaCare App
2. Logs into his or her account using assigned username and password (access credentials)
3. Search for existing case (pregnant woman)
4. View previous data entered (in the Registration Form) and update if necessary 
5. Save the case (pregnant woman) data.

Closing a Case:
1. CHW opens MamaCare App
2. Logs into his or her account using assigned username and password (access credentials).
3. Search for existing case (pregnant woman).
4. View previous data entered (in the Registration & Home Visit Forms) and update if necessary.
5. Open the Pregnancy Outcome Form and enter additional necessary information
6. Save the case data.
7. Remove the case or pregnant woman


MamaCare Mobile Application Overview:

MamaCare is a mobile-based application built on the Android platform for Community Health Workers (CHWs) to help monitor or track the health status of pregnant women from the time they conceive up to when they give birth or deliver. 
A Community Health Worker (CMW) starts by logging into his/her account in order to have access to the app’s functionality or features such as;

1. Viewing existing/enrolled pregnant women on a dashboard; 
2. Registration of a new pregnant woman; 
3. Selecting a pregnant woman, viewing her visit status and updating additional information collected during visit.

Test Cases - Developed:

1.(CMW) starts by logging into his/her account

2.Views Dashboard
The dashboard displays a list of all the women registered. A woman is displayed Active if she has not completed all the three required visits. 
When she completes the all her visits her status changes to completed. The dashboard has two buttons, Refresh and Register. 
The refresh button is used to refresh the women display list where the register button is used to record new women in the app.

3.Register New Record

4.Viewing woman specific dashboard
From the dashboard, a user will be able to select and view a specific dashboard for the selected woman. 
Through the specific woman dashboard, the user will be able to add, view, edit and delete the visits attended.

5.Recording and completing visits
The user will be able to register the visit and the vaccine administered.


