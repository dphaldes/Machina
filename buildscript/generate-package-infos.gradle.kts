import java.util.regex.Matcher

tasks.register<Task>("generatePackageInfos") {
    doLast {
        fileTree("src/main/java").forEach { javaFile ->
            val packageInfoFile = File(javaFile.parent, "package-info.java")
            if (!packageInfoFile.exists()) {
                var pkgName = javaFile.toString().replace(Matcher.quoteReplacement(File.separator), "/")
                pkgName = pkgName.substring(pkgName.indexOf("com/mystchonky/"), pkgName.lastIndexOf("/"))
                pkgName = pkgName.replace("/", ".")

                val pkgInfoText = """
                    |@FieldsAreNonnullByDefault
                    |@MethodsReturnNonnullByDefault
                    |@ParametersAreNonnullByDefault
                    |package $pkgName;
                    |
                    |import javax.annotation.ParametersAreNonnullByDefault;
                    |import net.minecraft.FieldsAreNonnullByDefault;
                    |import net.minecraft.MethodsReturnNonnullByDefault;
                """.trimMargin().trim()

                packageInfoFile.writeText(pkgInfoText)
            }
        }
    }
}