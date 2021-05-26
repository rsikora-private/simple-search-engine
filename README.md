## **Simple Search Engine**

The coding challenge is about writing simple command-line test search engine. It should read all the text files in the
given directory, building an in-memory representation of the files and their contents, and then give a command prompt at
which interactive searches can be performed. The search should take the words given on the prompt and return a list of
the top 10 (maximum) matching filenames in rank order, giving the rank score against each match.

### **Ranking**

● _The rank score must be 100% if a file contains all the words_
● _It must be 0% if it contains none of the words
● It should be between 0 and 100 if it contains only some of the words but the exact ranking formula
is up to you to choose and implement_

### **Requirements**

Java 11

### **Development**

##### Build

Linux `./mvnw clean package` 

Windows `mvnw clean package`

##### Run
`cd target`

and then

`java -jar simple-search-engine-1.0-SNAPSHOT.jar directoryContainingTextFiles`

**Improvements**

* improve LineSpliterator; now splits lines into words by whitespaces only
* improve word comprising; now program does exact match  
* indexing too many files in process memory may lead to OOM. Solution could be in-memory cache (Redis)
* improve speed by doing parallel indexing and searching














