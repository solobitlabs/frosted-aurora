# Frosted Aurora

SDK Compose Multiplatform (KMP) para backgrounds animados com degradês radiais elípticos e efeito fosco/leitoso.

**Autor:** Willian Santos — [solobit.com.br](https://solobit.com.br)  
**Licença:** [Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)](https://creativecommons.org/licenses/by-nc/4.0/)

---

## Como usar no seu projeto KMP (via Maven Central — sem token)

Como `mavenCentral()` já vem habilitado por padrão no `settings.gradle.kts`, basta adicionar a dependência em `commonMain`:

```kotlin
commonMain.dependencies {
    implementation("br.com.solobit:frosted-aurora:1.0.1")
}
```

### Exemplo de uso (Telas e Cards)
```kotlin
// Tela inteira com detecção automática de claro/escuro
FrostedAuroraBox(
    modifier = Modifier.fillMaxSize(),
    speed = 1f,
    isDark = isSystemInDarkTheme()
) {
    // Seu conteúdo aqui
}

// Dentro de um Card
Card(shape = RoundedCornerShape(24.dp)) {
    FrostedAuroraBox(
        palette = FrostedAuroraDefaults.OceanPalette,
        speed = 0.7f,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Resumo da Despensa", modifier = Modifier.padding(20.dp))
    }
}
```

---

## Como configurar a publicação no Maven Central (1ª vez)

1. Crie uma conta em [central.sonatype.com](https://central.sonatype.com) e solicite o namespace **`br.com.solobit`** (validando o registro `TXT` no DNS do domínio `solobit.com.br`).
2. Em **Account → Generate User Token**, gere o token (`username` e `password`).
3. Gere uma chave GPG e envie a chave pública para um keyserver:
   ```bash
   gpg --full-generate-key
   gpg --keyserver keyserver.ubuntu.com --send-keys SEU_KEY_ID
   gpg --export-secret-keys --armor SEU_KEY_ID
   ```
4. No repositório GitHub (`Settings → Secrets and variables → Actions`), cadastre os secrets:
   - `MAVEN_CENTRAL_USERNAME`
   - `MAVEN_CENTRAL_PASSWORD`
   - `SIGNING_IN_MEMORY_KEY` (saída do `--export-secret-keys --armor`)
   - `SIGNING_IN_MEMORY_KEY_ID` (8 últimos caracteres do Key ID)
   - `SIGNING_IN_MEMORY_KEY_PASSWORD` (senha da chave GPG)
5. Crie e envie uma tag `v*` (ou execute **Publish & Release** manualmente na aba **Actions**).
