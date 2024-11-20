package co.simplon.socworkbusiness.dtos;

public record AccountSignIn(String username, String password) {
    @Override
    public String toString() {
        return String.format("{username='%s', password=[PROTECTED]}", username, password);
    }
}
