![GitHub](https://img.shields.io/github/license/subratamazumder/pdf-utility-java?style=for-the-badge)
# PDF-Utility-Java
Very often we need to decrypt, merge & split sensitive pdf files (e.g.; payslips, tax return, medical report etc) into one big pdf for an easy upload. 
While using similar free online or paid tool may not be safe and could cause fraud in the future.
Hence this little java utility can be used by developers locally to do the followings.
- Merge many pdf files into a single pdf
- Split many pdf files into a many pdf
- Decrypt many pdf files given a password
## Usage
```console
git clone https://github.com/subratamazumder/pdf-utility-java.git

cd pdf-utility-java

./gradlew clean build jar

java -jar ./build/libs/pdf-utility-2.0-SNAPSHOT.jar MERGE /my/directory/which/has/pdffiles
java -jar ./build/libs/pdf-utility-2.0-SNAPSHOT.jar DECRYPT /my/directory/which/has/pdffiles password
java -jar ./build/libs/pdf-utility-2.0-SNAPSHOT.jar DECRYPT /my/directory/which/has/pdffiles password
java -jar ./build/libs/pdf-utility-3.0-SNAPSHOT.jar SPLIT /my/directory/which/has/pdffiles <splitAtPage, e.g.; 1>
```
### MERGE - Sample Output

```console
subratas-MacBook-Pro  ~/workspace/pdf-utility-java   master ●  ./gradlew clean build jar
BUILD SUCCESSFUL in 3s
3 actionable tasks: 3 executed
subratas-MacBook-Pro  ~/workspace/pdf-utility-java   master ● 
subratas-MacBook-Pro  ~/workspace/pdf-utility-java   master  java -jar build/libs/pdf-utility-1.0-SNAPSHOT.jar MERGE /Users/subratamazumder/workspace/Payslips
************************* Welcome To PDF Utility ****************************

Developed by Subrata Mazumder @ https://subratamazumder.github.io


************************* MERGE UTILITY ****************************
Reading directory /Users/subratamazumder/workspace/Payslips


Adding 00000_February2019.pdf
Adding 00000_October2019.pdf
Adding 00000_December2019.pdf
Adding 00000_February2020.pdf
Adding 00000_March2019.pdf
Adding 00000_July2019.pdf
Adding 00000_November2019.pdf
Adding 00000_May2019.pdf
Adding 00000_August2019.pdf
Adding 00000_January2020.pdf
Adding 00000_September2019.pdf
Ignoring !!! 2020-03-07-23-39-05-Auto-Merged.pdf
Adding 00000_June2019.pdf
Adding 00000_April2019.pdf


************************* SUMMARY ****************************

/Users/subratamazumder/workspace/Payslips/2020-03-07-23-40-10-Auto-Merged.pdf created successfully :) :)
Total no of pdf files-14
Total no of files merged-13
Total no of files ignored-1

*************************************************************

Total Execution Time (ms)-357
subratas-MacBook-Pro  ~/workspace/pdf-utility-java   master 

```

### DECRYPT - Sample Output

```console
java -jar build/libs/pdf-utility-1.0-SNAPSHOT.jar DECRYPT /Users/subratamazumder/workspace/Payslips/decrypt pass1234


************************* Welcome To PDF Utility ****************************

Developed by Subrata Mazumder @ https://subratamazumder.github.io



************************* DECRYPT UTILITY ****************************
Reading directory /Users/subratamazumder/workspace/Payslips/decrypt
Ignoring !!! paySlip_195881_July2019.pdf, not an encrypted pdf
Ignoring !!! paySlip_195881_April2019.pdf, not an encrypted pdf
Ignoring !!! paySlip_195881_December2019-decrypted.pdf, previously decrypted pdf
paySlip_195881_December2019.pdf decrypted successfully :) :)
Ignoring !!! paySlip_195881_August2019.pdf, not an encrypted pdf


************************* SUMMARY ****************************
Total no of pdf files-5
Total no of files decrypted-1
Total no of files ignored-4

*************************************************************

Total Execution Time (ms)-257

```
### SPLIT - Sample Output
```console
java -jar /Users/subratamazumder/workspace/pdf-utility-java/build/libs/pdf-utility-3.0-SNAPSHOT.jar SPLIT /Users/subratamazumder/workspace/marksheet/split 4


************************* Welcome To PDF Utility ****************************

Developed by Subrata Mazumder @ https://subratamazumder.github.io



************************* SPLIT UTILITY ****************************
Reading directory /Users/subratamazumder/workspace/marksheet/split


Reading directory /Users/subratamazumder/workspace/marksheet/split
SubrataMazumder_00xxxx_MarkSheet.pdf splitted successfully :) :)


************************* SUMMARY ****************************
Total no of pdf files-1
Total no of files splitted-1
Total no of files ignored-0

*************************************************************

Total Execution Time (ms)-488

ll
total 27816
-rw-r--r--  1 subratamazumder  staff   2.8M  9 Oct 16:44 SubrataMazumder_00xxxx_MarkSheets-1-Splitted.pdf
-rw-r--r--  1 subratamazumder  staff   3.9M  9 Oct 16:44 SubrataMazumder_00xxxx_MarkSheets-2-Splitted.pdf
-rw-r--r--@ 1 subratamazumder  staff   6.9M  9 Oct 16:30 SubrataMazumder_00xxx_MarkSheets.pdf
```

### Publish New Version
```console
export GITHUB_TOKEN=xxxxxx
export GITHUB_USERNAME=xxxxxx
./gradlew publish

```
### Contribute

Feel free to fork and raise PR with sensible commit & change description
