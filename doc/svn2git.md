# Migrate https://scm.wald.intevation.org/svn/auik/ to git

Using <https://github.com/nirvdrum/svn2git>

## Create authors.txt

To convert all your svn authors to git format, create a file somewhere on your
system with the list of conversions to make, one per line.

Fetch users from remote svn.

```
svn log --quiet https://scm.wald.intevation.org/svn/auik/ | grep -E "r[0-9]+ \| .+ \|" | cut -d'|' -f2 | sed 's/^ //' | sort | uniq > svn_authors.txt
```

Edit svn_authors.txt

## Initial Conversion
```
mkdir auik-git-repo
cd auik-git-repo
svn2git https://scm.wald.intevation.org/svn/auik/ --authors ../svn_authors.txt
```

## Push to Github

```
git remote add origin git@github.com:stadt-bielefeld/auik.git
git push -u origin master
```

Done.
