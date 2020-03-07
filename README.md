# pdf-utility-java
Very often we need to merge sensitive pdf files (e.g.; payslips, tax return, medical report etc) into one big pdf for easy upload. 
But using online pdf joiner may not safe and could cause fraud in future.
Hence this little java utility can be used locally to merge many pdf files into one file.
## Usage
```./gradlew clean build jar
```java -jar pdf-utility-1.0-SNAPSHOT.jar

Sample Output
```
subratas-MacBook-Pro  ~/workspace/pdf-utility-java   master ●  ./gradlew clean build jar

BUILD SUCCESSFUL in 3s
3 actionable tasks: 3 executed
subratas-MacBook-Pro  ~/workspace/pdf-utility-java   master ● 


subratas-MacBook-Pro  ~/workspace/pdf-utility-java   master ●  java -jar build/libs/pdf-utility-1.0-SNAPSHOT.jar
Adding 195881_February2019.pdf
Adding 195881_October2019.pdf
Adding 195881_December2019.pdf
Adding 195881_February2020.pdf
Adding 195881_March2019.pdf
Adding Onsite_195881_February2019.pdf
Adding 195881_July2019.pdf
Adding 195881_November2019.pdf
Adding 195881_May2019.pdf
Adding 195881_August2019.pdf
Adding 195881_January2020.pdf
Adding 195881_September2019.pdf
Ignoring !!!2020-03-07-21-41-30-Auto-Merged.pdf
Ignoring !!!2020-03-07-21-43-49-Auto-Merged.pdf
Adding 195881_June2019.pdf
Adding 195881_April2019.pdf
Adding Onsite_195881_January2019.pdf


************************* SUMMARY ****************************

2020-03-07-21-47-53-Auto-Merged.pdf created successfully :) :)
Total no of pdf files-17
Total no of files merged-15
Total no of files ignored-2

*************************************************************

Total Execution Time (ms)-378
 subratas-MacBook-Pro  ~/workspace/pdf-utility-java   master ● 
```
