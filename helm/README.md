# ImshaBy API & MongoDB helm charts

## ImshaBy API
### Install helm chart

```bash
> helm install --name <release_name>  \
--namespace imsha-by \
--set activeProfile=<profile name>
-f ./imshaby-api/values-<env_to_deploy>.yaml ./imshaby-api
```

### Delete helm chart
```bash
> helm delete --name <release_name>  \
--purge
```

###EXAMPLE
```bash
> helm install --name imshaby-api  \
--namespace imsha-by \
-f ./imshaby-api/values-local.yaml ./imshaby-api
> 
> helm delete --name imshaby-api
```

## MongoDB
### Install helm chart

```bash
> kubectl -n imsha-by apply  \
-f ./mongodb/k8s-pvc.yaml
>
> helm install --name <release_name>  \
--namespace imsha-by \
--set activeProfile=<profile name>
-f ./mongodb/values-<env_to_deploy>.yaml stable/mongodb
```

### Delete helm chart
```bash
> helm delete --name <release_name>  \
--purge
```

###EXAMPLE
```bash
> kubectl -n imsha-by apply  \
-f ./mongodb/k8s-pvc.yaml
>
> helm install --name mongodb  \
--namespace imsha-by \
-f ./mongodb/values-local.yaml stable/mongodb
> 
> helm delete --name mongodb
```