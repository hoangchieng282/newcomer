import argparse
from git import Repo

# Author: chih
# Time modified:   09/23/2022
parser = argparse.ArgumentParser()

# group = parser.add_mutually_exclusive_group()

# **************************************** Argument declare *******************************************
parser.add_argument("-r", "--repopath", type=str, help="the path to your local git repository. Just leave it empty")

parser.add_argument("-b", "--branch", type=str, help="Branch to be archived")

parser.add_argument("-t", "--target", type=str, help="target branch to store the archived")

parser.add_argument("-u", "--username", type=str, help="Your username for git login")

parser.add_argument("-p", "--password", type=str, help="Your otp if you're using github or your password for bitbucket")

args = parser.parse_args()
branch = args.branch
target = args.target
repopath = args.repopath if args.repopath else "."
username = args.username
password = args.password
# **************************************** // *******************************************
repo =  Repo(repopath)              # Repo object set to cloned repo
assert not repo.bare                # Make sure repo isn't empty
remote = repo.remote()              # Set to a remote object (Defaults to 'origin') can override with name=...

# try:
#     for repo_branch in repo.references:         # loop over the remote branches
#         # for branch in branches:                 
#             # if target in repo_branch.name:      # does the branch you want to delete exist in the remote git repo? 
#                 print("deleting remote branch: %s" % repo_branch)# %s" % repo_branch.remote_head)
#                 if str(repo_branch) == "origin/release/1.0.4":
#                     remote.push(refspec=(":%s" % repo_branch.remote_head)) # remote_head = the branch you want to delete Example: "origin/my-branch"
# except:
#     print("error")

try:
    print(target)
    # aiss = repo.git.checkout(target)
    # print(aiss)
except:
    raise Exception("Your branch doesn't exist")
    # print("Your branch doesn't exist")

print("ok")