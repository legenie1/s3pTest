## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

# Creating out folder
mkdir -p out

<!-- ############################### PING ENDPOINT  ############################ -->
# To Compile Ping
javac -cp ".:lib/smobilpay-s3p-java-client-1.0.0.jar" -d out src/src/Check.java

# To Execute Ping
<!-- This can be used windows  -->
java -cp ".:lib/smobilpay-s3p-java-client-1.0.0.jar:out" Check

# Execute with updated dependecies
<!-- This is for macos or linux -->
java -cp ".:lib/*:out" Check 

<!-- ############################### CASHOUT FLOW  ############################ -->

# To Compile Cahout Flow
javac -cp ".:lib/smobilpay-s3p-java-client-1.0.0.jar" -d out src/src/CashOutCollectionExample.java

# To Execute Cashout Flow
<!-- This can be used windows -->
java -cp ".:lib/smobilpay-s3p-java-client-1.0.0.jar:out" CashOutCollectionExample

# Execute with updated dependecies
<!-- This is for macos or linux -->
java -cp ".:lib/*:out" CashOutCollectionExample 
