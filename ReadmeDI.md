<-----------------------------------
Why Do We Need DI
- To make class testable
- To make code extensible
- Single responsibility
- Lifetime of object or its scope or re-usability of code
<-----------------------------------

Dagger
1. Dagger is fully static, compile time dependency injection framework
2. It Checks compile time, if dagger is able to create required object by consumer
3. Annotation based
4. Dagger behave as System which is able to create object for us
5. Dagger helps to inject these objects
6. Dagger manages lifetime of these objects (eg. singleton)
7. Dagger helps to us to write manageable code
------------------------------------

Dagger Concept
1. Consumer (@Inject)
2. Producer (@Module, @Provides, @Bind)
3. Connector (@Component)
------------------------------------

