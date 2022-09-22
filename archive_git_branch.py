import argparse
from msilib.schema import Error
from git import Repo


parser = argparse.ArgumentParser()

# group = parser.add_mutually_exclusive_group()

parser.add_argument("-r", "--repo", type=str,
                   help="the git repository")

parser.add_argument("-b", "--branch", type=str,
                   help="Branch to be archived")

parser.add_argument("-t", "--target", type=str,
                   help="target branch to store the archived")

parser.add_argument("-u", "--username", type=str,
                   help="Your username for git login")

parser.add_argument("-p", "--password", type=str,
                   help="Your otp if you're using github or your password for bitbucket")

args = parser.parse_args()
branch = args.branch
target = args.target
repo = args.repo if args.repo else "c:/Users/chih/Desktop/newcomer"
# the default value here is just my local path to the custom git folder. You have to modified the path in order to use this cript on default
username = args.username
password = args.password

repos =  Repo(repo)
try:
    aiss = repos.git.checkout(target)
    print(aiss)
except:
    # raise Error("Your branch doesn't exist")
    print("Your branch doesn't exist")

print("ok")