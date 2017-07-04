## Hibernate Reverse Engineer Directory

The files contained in the directory are used by or created from the Hibernate Reverse Engineer Eclipse plugin. This plugin connects to a database and automatically generates Java entity classes based on the schema.

The purpose of this is to be able to run a diff between database changes to easily see what we will need to update on the entities. We cannot compare the _generated entities_ to our _actual entities_ because there are a lot of changes that we make to our actual entities after generation. Instead, we can run a diff from the _generated entities_ of one commit to the _generated entities_ of another commit to see what we need to add/remove/change in our actual entities. 

All of the files in this dir are configuration used to run the eclipse plugin except for files in the /target dir which are the generated entities to be used for comparison.