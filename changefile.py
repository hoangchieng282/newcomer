import os, shutil
import git

def clone_repo (remote_url, local_path, branch):                               # Clone a remote repo
    print("Cloning repo %s" % remote_url)
    if os.path.isdir(local_path) is True:
        shutil.rmtree(local_path)
    git.Repo.clone_from(remote_url, local_path, branch=branch)

def delete_remote_branches(remote_url, branches):
    cur_dir = os.path.dirname(__file__)
    local_path="%s/%s" % (cur_dir,repo)

    clone_repo(
        remote_url=remote_url,                  # The clone url                                                           
        local_path=local_path,                  # Dir path where you want to clone to
        branch="master"                         # Branch to clone (Note: Cannot be on the same branch you want to delete)
    )
    repo = git.Repo(local_path)                 # Repo object set to cloned repo
    assert not repo.bare                        # Make sure repo isn't empty
    remote = repo.remote()                      # Set to a remote object (Defaults to 'origin') can override with name=...
    for repo_branch in repo.references:         # loop over the remote branches
        for branch in branches:                 
            if branch in repo_branch.name:      # does the branch you want to delete exist in the remote git repo? 
                print("deleting remote branch: %s" % repo_branch.remote_head)
                remote.push(refspec=(":%s" % repo_branch.remote_head)) # remote_head = the branch you want to delete Example: "origin/my-branch"

# Note: Could add some logic to delete your local cloned repo when you're done
print("hi")