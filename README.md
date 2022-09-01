# DocumentStore

Built a search engine using many different data structures, in 5 stages over the course of the semester. 

Stage 1: In-Memory Document Store
Built a storage mechanism which supports “getting” and “putting” data. Documents are stored in memory in a hash table and can only be retrieved using the key (URI) with which they are stored. Documents can be plain text, a.k.a. a String, or binary data, e.g. images.

Stage 2: Add Undo Support to the Document Store Using a Stack
Added support for two different types of undo:
1) undo the last action, no matter what document it was done to 
2) undo the last action on a specific Document.

Stage 3: Keyword Search Using a Trie. 
Added key word search capability to the document store. That means a user can call DocumentStore.search(keyword) to get a list of documents in the document store that contain the given keyword. The data structure used for searching is a Trie. 

Stage 4: Memory Management, Part 1: Tracking Document Usage via a Heap.
Used a min Heap to track the usage of documents in the document store. Only a fixed number of documents are allowed in memory at once, and when that limit is reached, adding an additional document results in the least recently used document being deleted from memory.

Stage 5: Memory Management, Part 2: Two Tier Storage (RAM and Disk) Using a BTree. 
Previously, a document that had to be removed from memory due to memory limits was simply erased from existence. In this stage, it writes it to disk and bring it back into memory if it is needed. It continues to use a MinHeap to track the usage of documents in the document store, and continues to use the Trie for keyword search. The HashTable is replaced with a BTree for storing your documents. While the BTree itself will stay in memory, the documents it stores can move back and forth between disk and memory, as dictated by memory usage limits.
