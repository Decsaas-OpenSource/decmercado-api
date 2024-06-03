# Gateway

## Estrutura dos arquivos.

*Privado*
```
-----BEGIN PRIVATE KEY-----
...
-----END PRIVATE KEY-----
```

*Publico*
```
-----BEGIN CERTIFICATE-----
...
-----END CERTIFICATE-----
```

> [!NOTE]
> O nome dos arquivos gerados devem ser colocados no arquivo de config do **config-server**
> - application-security.yaml
```
jwt:
  private: <nome_do_arquivo>.key
  public: <nome_do_arquivo>.pub
```