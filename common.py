from colorama import init, Fore, Style

init()


def blue(text: str):
    return f"{Fore.BLUE}{text}{Style.RESET_ALL}"


def red(text: str):
    return f"{Fore.RED}{text}{Style.RESET_ALL}"


def green(text: str):
    return f"{Fore.GREEN}{text}{Style.RESET_ALL}"


def purple(text: str):
    return f"{Fore.MAGENTA}{text}{Style.RESET_ALL}"