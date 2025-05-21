package HC.Banco_Talentos.Utils;

public class DocumentoUtils {

    public static boolean cpfValido(String cpf) {
        if (!cpf.matches("\\d{11}")) {
            return false;
        }

        int[] pesos = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        if (isRepetitiveDigits(cpf)) {
            return false;
        }
        int digito1 = calcularDigitoVerificador(cpf.substring(0, 9), pesos);
        int digito2 = calcularDigitoVerificador(cpf.substring(0, 9) + digito1, new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2});

        return cpf.equals(cpf.substring(0, 9) + digito1 + digito2);
    }

    private static int calcularDigitoVerificador(String documento, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < documento.length(); i++) {
            soma += Character.getNumericValue(documento.charAt(i)) * pesos[i];
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }

    private static boolean isRepetitiveDigits(String documento) {
        return documento.chars().distinct().count() == 1;
    }
}
