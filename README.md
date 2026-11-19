# Frosted Aurora

SDK Compose Multiplatform (KMP) para backgrounds animados com degradês radiais elípticos e efeito fosco/leitoso.

**Autor:** Willian Santos — [solobit.com.br](https://solobit.com.br)  
**Licença:** [Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)](https://creativecommons.org/licenses/by-nc/4.0/)

---

## Como publicar no GitHub Packages

1. Crie um repositório no GitHub (`solobitlabs/frosted-aurora`) e faça push deste diretório.
2. O workflow em `.github/workflows/publish.yml` publica automaticamente ao criar uma tag `v*` (ex: `git tag v1.0.0 && git push --tags`) ou via `workflow_dispatch`.
3. Para publicar localmente via Gradle:
   ```bash
   GITHUB_ACTOR=seu_usuario GITHUB_TOKEN=seu_pat ./gradlew :frosted-aurora:publishAllPublicationsToGitHubPackagesRepository
   ```

---

## Como usar no projeto Despensa (KMP)

### 1. Adicione o repositório no `settings.gradle.kts`
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/solobitlabs/frosted-aurora")
            credentials {
                username = providers.gradleProperty("gpr.user").orNull ?: System.getenv("GITHUB_ACTOR")
                password = providers.gradleProperty("gpr.key").orNull ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
```

### 2. Adicione a dependência em `commonMain`
```kotlin
commonMain.dependencies {
    implementation("br.com.solobit:frosted-aurora:1.0.0")
}
```

### 3. Use em telas ou Cards
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
