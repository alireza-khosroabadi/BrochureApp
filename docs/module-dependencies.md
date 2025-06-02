# Module Dependencies Graph

```mermaid
graph LR
    App[App] --> Feature[Feature]
    App --> CoreUI[Core UI]
    App --> Design[Design]
    
    Feature --> Domain[Domain]
    Feature --> CoreUI
    Feature --> Design
    
    Domain --> Data[Data]
    
    CoreUI --> Design
    
    style App fill:#f9f
    style Feature fill:#bbf
    style CoreUI fill:#bfb
    style Design fill:#bfb
    style Domain fill:#fbb
    style Data fill:#fbb
```

## Module Dependencies

- **App** → Feature, Core UI, Design
- **Feature** → Domain, Core UI, Design
- **Domain** → Data
- **Core UI** → Design
- **Design** → None
- **Data** → None 