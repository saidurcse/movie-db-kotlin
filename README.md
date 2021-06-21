# Movie-db-kotlin
<snippet>
  <content><![CDATA[
# ${1:Project Name}

![License](https://camo.githubusercontent.com/c97d380d0a98377c53391026883a89c16ded751eb41f9e57a53e009664447d50/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6c6963656e73652d4d49542532304c6963656e73652d626c75652e737667)
[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

A simple project for The Movie DB based on Kotlin MVVM with Navigation clean architecture.

## Build Process

Add your Movie db api url and image url with API key in local.properties file.

api_url=https://api.themoviedb.org/

image_url=https://image.tmdb.org/t/p/w780

api_key=YOUR_API_KEY

## Development process

Based on Test-driven development.

    1) API Service -> API Service Unit Test with api response mock files
    2) ViewModel -> ViewModel Unit Test
    3) Implmentating UI & Layouts

## Specs & Open-source libraries


    > Minimum SDK 21
    - 100% Kotlin based
    - MVVM Architecture
    - Architecture Components (Lifecycle, LiveData, ViewModel, Room Persistence)
    - DataBinding
    - Material Design & Animations
    - The Movie DB API
    - Retrofit2 & Gson for constructing the REST API
    - OkHttp3 for implementing interceptor, logging and mocking web server
    - Glide for loading images
    - BaseRecyclerViewAdapter for implementing adapters and viewHolders
    - Mockito-kotlin for Junit mock test


## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## License

```sh
The MIT License (MIT)

Copyright (c) 2021 Saidur

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```

</content>
</snippet>
