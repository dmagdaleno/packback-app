# PackbackApp

Aplicativo Android que consome os endpoints do serviço PackBack.

O aplicativo permite listar as coletas realizadas pelo usuário e visualizar sua pontuação.

Além disso, com o aplicativo é possível identificar produtos nas proximidades que garantem uma 
pontuação na devolução da embalagem.

## Tecnologias utilizadas

- Kotlin (1.3.41)
- Android Architecture Lifecycle (2.2.0-alpha03)
- Retrofit (2.4.0)
- Room (1.1.1)
- Android Beacon Library (2+)
- API PackBack (0.0.6)

## Para acessar o aplicativo

- Usuário: usuario@teste.com
- Senha: 123456

## Acessando dispositivos IoT

Após o Login, clicar no botão "Buscar Produtos"

Será exibida uma lista de produtos diferentes dependendo do Beacon que for identificado.

Para isso, os Beacons precisam ser configurados da seguinte forma:

**Beacon 1**

*Eddystone UID*

Instance ID (id2): 000000000001

**Beacon 2**

*Eddystone UID*

Instance ID (id2): 000000000002

**Beacon 3**

*Eddystone UID*

Instance ID (id2): 000000000003

**Beacon 4**

*Eddystone UID*

Instance ID (id2): 000000000004
