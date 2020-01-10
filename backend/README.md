## Parsing status pages architecture

`StatusPageFetcher`
1. entry --> connection --> service raw info (entry + response body (HashMap)) `StatusPageService`
1. response body --> Service object `StatusPageParser`
