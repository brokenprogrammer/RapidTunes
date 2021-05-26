# RapidTunes

A music player that lets you play music from all sources.

The goal of RapidTunes is to let you collect all your music from various sources in one application.

## Installation

Clone the project:

`git clone https://github.com/brokenprogrammer/RapidTunes.git`

Move into the RapidTunes directory:

`cd RapidTunes`

Install dependencies:

`npm i`

Install EVS:

`% python3 -m pip install --upgrade castlabs-evs`

Create an EVS account:

`% python3 -m castlabs_evs.account signup`

VMP-sign your application:

`% python3 -m castlabs_evs.vmp sign-pkg node_modules/electron/dist`

## License

RapidTunes is maintained under the [MIT License](https://github.com/brokenprogrammer/RapidTunes/blob/master/LICENSE).
