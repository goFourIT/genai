# Notes

- Llama v3.2 with 8b has a context window of 8.192 tokens.


## Classes

### Prompt
A `Prompt` has a list of `Message` and `ChatOptions`.
A `Prompt` is made of multiple different types of `Message`
 
### Message
A `Message` has a `String` content and a `Map` for metadata.

A `MediaMessage` has in addition a `Collection` of `Media`

### PromptTemplate

### VectorStore
Configure a client for communicating with **Vector database**.

### Document
A `Document` has a `String` content and a `Map` for metadata.