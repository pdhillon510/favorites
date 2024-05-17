# favorites

A sample application for building out a pipeline using GitHub Actions.

## Local testing

### Building

```shell
mvn verify
```

This will compile, run the unit tests, and then run the integration tests.

### Running

```shell
mvn spring-boot:run
```

This will compile and run the Spring Boot application as a server,
listening to port 8080.

<kbd>Ctrl</kbd><kbd>C</kbd> to quit.

### Mappings

* `/` will return "Hello, World!" and the current time.
* `/actuator/health` will return a JSON response with the up/down status.

### Docker

```shell
docker build -t otherdevopsgene/favorites .
```

This will create a Docker image tagged `otherdevopsgene/favorites`.

## Exercise: Make a change using TDD and Git

Use a Cloud9 instance.

Run the unit tests to see them pass.

```shell
mvn test
```

The first run will be slow. Run again to see it go faster, if you like.

Navigate in the left tree view to
`favorites/src/test/java/dev/otherdevopsgene/favorites` and open
`FavoritesControllerTest.java` in the editor.

Change the tests for the name and favorites statements. Save.

Run the unit tests to see them fail. 

Navigate in the left tree view to `favorites/src/main/resources` and open
`application.properties` in the editor. 

Change the values for the name, favorite fruits, and favorite color. Save.

Run the unit tests to see them pass. 

Use the terminal window at the bottom to add and commit the changes to Git
locally.

## Exercise: Run a full build and push to our CI engine

Run the build, unit tests, other dev tests, and packaging (to a `.jar` file) to 
make sure it works.

```shell
mvn verify
```

Push the Git changes to GitHub.

```shell
git push origin
```

In your browser, navigate to your GitHub repository and select **Actions**
across the top. Click into the build job to see it run. 

Back in Cloud9, navigate to `favorites/.github/workflows` and open `maven.yml`
in the editor. 

Compare to `extras/Jenkinsfile`.

## Exercise: Set up static analysis

Create a branch so we can trigger the action on the pull request.

```shell
git checkout -b add-sca
```

Move `favorites/extras/codeql.yml` into `favorites/.github/workflows`.

Commit the changes (you'll need `rm` and `add`).

Push the Git changes to GitHub.

```shell
git push origin
```

In GitHub, select **Actions** across the top. Click into the **Analyze** job to
see it run. 

In GitHub, select **Security** across the top and then **Code scanning** on the
left to see the results. 

## Exercise: Set up SCA

Create a branch so we can trigger the action on the pull request.

```shell
git checkout -b add-sca
```

Move `favorites/extras/sca.yml` into `favorites/.github/workflows`.

Commit the changes and push them to GitHub. 

In GitHub, create a pull request to merge `add-sca` into `main`.

In the pull request, select the **Dependency Review** check to watch it run.

In GitHub, select **Actions** across the top. Click into the 
**Dependency Review** job to see the results.

Review (and experiment with, if you want) the license options in the action.
- https://github.com/actions/dependency-review-action

Merge the pull request.

Locally, switch back to the main branch and fetch and merge the changes.

```shell
git checkout main
git fetch origin
git merge
```

## Exercise: Run some containers

Run the `hello-world` container image.

```shell
docker run hello-world
```

The first run needs to download the image. Run again to see it use the cache.

Run an `ubuntu` image interactively.

```console
docker run -it --rm ubuntu bash
# exit
```

## Exercise: Build our application image

Copy `favorites/extras/Dockerfile` up one level into the `favorites` directory.

Build our image. (*Note the trailing dot*)

```shell
docker build -t otherdevopsgene/favorites .
```

Tag this image version.

```shell
docker tag otherdevopsgene/favorites:latest otherdevoppsgene/favorites:big
```

Run our image.

```shell
docker run -it --rm -p 8080:8080 otherdevopsgene/favorites
```

Hit our application from a separate terminal.

```shell
curl http://localhost:8080/
```

See what Docker knows.

```shell
docker ps
docker images
docker history otherdevopsgene/favorites
```

Quit our application from the original terminal with `Ctrl-C`.

## Exercise: Build a smaller image

Replace the `FROM` line in `Dockerfile` with

```dockerfile
FROM eclipse-temurin:21-jre-alpine
```

Rebuild the image.

Notice the image size now.

```shell
docker images
docker history otherdevopsgene/favorites
```

Quit our application from the original terminal with `Ctrl-C`.

Create a branch to commit your changes, then submit a pull request and merge it if all the checks pass.

## Exercise: Scan our image

Pull the `anchore/grype` image from Docker Hub.

```shell
docker pull anchore/grype
```

Scan our image using Grype.

```shell
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \
  -v grype-repo:/.cache/ \
  anchore/grype otherdevopsgene/favorites:latest
```

Scan the bigger version of the image (`otherdevopsgene/favorites:big`) and compare the results.

## Exercise: My first Terraform server

Change to the `~/environments/favorites/extras/terraform-example` directory.

```shell
cd ~/environments/favorites/extras/terraform-example
```

Initialize the directory for Terraform.

```shell
terraform init
```

Edit the `Name` tag in `main.tf` to be your name.

Validate the syntax, see the planned changes, and apply them.

```shell
terraform validate
terraform plan
terraform apply
```

Use the AWS CLI to verify it is running. Fill in your name

```shell
aws ec2 describe-instances --filters "Name=tag:Name,Values=YourName" \
  --query "Reservations[].Instances[].State.Name"
```

Tear down any infrastructure you created.

```shell
terraform destroy
```

## Exercise: Running Ansible

Change to the `~/environments/favorites/extras/ansible-example` directory.

```shell
cd ~/environments/favorites/extras/terraform-example
```

Look at `surprise.txt`.

In the terminal, run the `surprise.yaml` playbook, which is similar to what Gene ran in the demo.

```shell
ansible-playbook surprise.yaml
```
