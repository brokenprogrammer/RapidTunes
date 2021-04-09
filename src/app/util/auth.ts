export function loginSpotify() {
    let clientId = '366c4d910b944c7d9d4a16c7b299b2ef';
    let redirectUri = 'rapidtunes://home';
    let scope = 'user-read-private%20user-read-email%20streaming';
    let url = 'https://accounts.spotify.com/authorize?client_id=' + clientId + '&response_type=code&redirect_uri=' + redirectUri + '&scope=' + scope;
    console.log(url);
    window.location.href = url;
}

export function initSpotify(): Promise<string> {
    return new Promise<string>(resolve => {
        const params = new URLSearchParams(window.location.search);

        let spotifyCode = params.get('spotify_code');
        let date = new Date();

        let str = localStorage.getItem('spotifyAuth');
        let spotifyAuth = str ? JSON.parse(str) : null;

        if (spotifyCode && (!spotifyAuth || spotifyAuth?.expire_date < date)) {
            authorizeSpotify(spotifyCode).then(result => {
                localStorage.setItem('spotifyAuth', JSON.stringify(result));
                resolve(spotifyAuth.access_token);
            });
        } else if (spotifyAuth?.expire_date < date) {
            refreshSpotifyAuth(spotifyAuth.refresh_token).then(result => {
                localStorage.setItem('spotifyAuth', JSON.stringify(result));
                resolve(spotifyAuth.access_token);
            });
        }
        resolve(spotifyAuth.access_token);
    });
}

export function authorizeSpotify(token: string): Promise<any> {
    let body = 'grant_type=authorization_code&' +
        'code=' + token + '&' +
        'redirect_uri=rapidtunes://home';

    return new Promise<any>(resolve => {
        requestSpotifyAuth(body).then(response => {
            let date = new Date();
            date.setSeconds(date.getSeconds() + response['expires_in']);
            response['expire_date'] = date;
            resolve(response);
        });
    });
}

export function refreshSpotifyAuth(token: string): Promise<any> {
    let body = 'grant_type=refresh_token&' +
        'refresh_token=' + token;

    return new Promise<any>(resolve => {
        requestSpotifyAuth(body).then(response => {
            let date = new Date();
            date.setSeconds(date.getSeconds() + response['expires_in']);
            response['expire_date'] = date;
            response['refresh_token'] = token;
            resolve(response);
        });
    })
}

function requestSpotifyAuth(body: string): Promise<any> {
    return new Promise<any>(resolve => {
        let xhttp = new XMLHttpRequest();
        xhttp.open('POST', 'https://accounts.spotify.com/api/token', true);
        xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhttp.setRequestHeader('Authorization', 'Basic MzY2YzRkOTEwYjk0NGM3ZDlkNGExNmM3YjI5OWIyZWY6NDk3MDJkYzA5ZTUxNGFiZTg4YzQ1NTFkOWNmNDRjYjk=');

        xhttp.send(body);
        xhttp.onreadystatechange = function () {
            if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
                resolve(JSON.parse(this.response));
            }
        }
    });
}