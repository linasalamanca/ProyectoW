export class JwtAuthenticationResponse {
    constructor(public token: string, public id: number, public user: string, public role: string) { }
}
