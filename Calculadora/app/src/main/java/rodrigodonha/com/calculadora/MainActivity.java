package rodrigodonha.com.calculadora;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Double.parseDouble;

public class MainActivity extends AppCompatActivity {
    /*
        O programa fará os calculos das operações de somar, subtrair, multiplicar, dividir,
        e porcentagem.

        Vai memorizar os resultados, somando-os e mostrantodo-os através dos botoes MC, M+,
        M- e MR.

        Também vai mostrar o histórico da operação acima do visor da calculadora.

        E acima do histórico terá um campo de mensagens ao usuário.

    */

    // botoes
    private Button botao0;
    private Button botao1;
    private Button botao2;
    private Button botao3;
    private Button botao4;
    private Button botao5;
    private Button botao6;
    private Button botao7;
    private Button botao8;
    private Button botao9;

    // Operadores
    private Button botaoSomar;
    private Button botaoSubtrair;
    private Button botaoDividir;
    private Button botaoMultiplicar;

    // Sinais
    private Button botaoVirgula;
    private Button botaoIgual;

    // Extras
    private Button botaoPorcentagem;
    private Button botaoNegativoPositivo;
    private Button botaoLimpar;

    // Memória
    private Button botaoMemoriaLimpar;
    private Button botaoMemoriaSomar;
    private Button botaoMemoriaSubtrair;
    private Button botaoMemoriaResultado;

    // Exibir
    private TextView txvVisor;
    private TextView txvHistorico;
    private TextView txvMensagem;

    // Valores
    Double valorA = (double)0;
    Double valorB = (double)0;
    Double valorC = (double)0;
    Double Resultado = (double)0;
    Double resultPorcent = (double)0;

    // Serviços
    String strVisor = "";
    String strOperacao = "";
    String strHistorico = "";
    String strMensagem = "";
    String strProxMovEsperado = "inserir valorA";

    final boolean[] porcentagem = {false};
    boolean trocaSinal = false;

    // Persistência
    private static String MINHAS_PREFERENCIAS = "Minhas Preferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        // Criando Objetos
        botao0 = (Button) findViewById(R.id.btZero);
        botao1 = (Button) findViewById(R.id.btUm);
        botao2 = (Button) findViewById(R.id.btDois);
        botao3 = (Button) findViewById(R.id.btTres);
        botao4 = (Button) findViewById(R.id.btQuatro);
        botao5 = (Button) findViewById(R.id.btCinco);
        botao6 = (Button) findViewById(R.id.btSeis);
        botao7 = (Button) findViewById(R.id.btSete);
        botao8 = (Button) findViewById(R.id.btOito);
        botao9 = (Button) findViewById(R.id.btNove);

        botaoSomar = (Button) findViewById(R.id.btSomar);
        botaoSubtrair = (Button) findViewById(R.id.btSubtrair);
        botaoMultiplicar = (Button) findViewById(R.id.btMultiplicar);
        botaoDividir = (Button) findViewById(R.id.btDividir);

        botaoVirgula = (Button) findViewById(R.id.btVirgula);
        botaoIgual = (Button) findViewById(R.id.btIgual);

        botaoPorcentagem = (Button) findViewById(R.id.btPorcentagem);
        botaoNegativoPositivo = (Button) findViewById(R.id.TrocaSinal);
        botaoLimpar = (Button) findViewById(R.id.btLimpar);

        botaoMemoriaSomar = (Button) findViewById(R.id.btMemSomar);
        botaoMemoriaSubtrair = (Button) findViewById(R.id.btMemSubtrair);
        botaoMemoriaResultado = (Button) findViewById(R.id.btMemResultado);
        botaoMemoriaLimpar = (Button) findViewById(R.id.btMemLimpar);

        txvVisor = (TextView) findViewById(R.id.txvVisor);
        txvHistorico = (TextView) findViewById(R.id.txvHistorico);
        txvMensagem = (TextView) findViewById(R.id.txvMensagem);



        // quando clica no M+
        botaoMemoriaSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // pega o conteudo gravado para comparar se tem ou não tem algo gravado.
                SharedPreferences dados_gravados = getSharedPreferences(MINHAS_PREFERENCIAS,0);
                String nomeUsuario = dados_gravados.getString("nome", "");
                txvMensagem.setText(nomeUsuario);

                // se tiver número gravado ele entra e soma o valor gravado ao valor do visor
                if (nomeUsuario!= ""){

                    Double ResultadoGravado = parseDouble(nomeUsuario.toString());
                    Double ResultadoVisor = parseDouble(txvVisor.getText().toString());
                    Double Somar = ResultadoGravado + ResultadoVisor;

                    SharedPreferences sharedPreferences = getSharedPreferences(MINHAS_PREFERENCIAS,0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nome", Somar.toString());
                    editor.commit();
                    txvMensagem.setText("Somou com sucesso");
                }
                // está funfando belezinha
                else{
                    SharedPreferences sharedPreferences = getSharedPreferences(MINHAS_PREFERENCIAS,0);

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("nome", txvVisor.getText().toString());

                    editor.commit();
                    txvMensagem.setText("Incluiu com sucesso");
                }
            }
        });

        botaoMemoriaSubtrair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // pega o conteudo gravado para comparar se tem ou não tem algo gravado.
                SharedPreferences dados_gravados = getSharedPreferences(MINHAS_PREFERENCIAS,0);
                String nomeUsuario = dados_gravados.getString("nome", "");
                txvMensagem.setText(nomeUsuario);

                if (nomeUsuario!= ""){

                    Double ResultadoGravado = parseDouble(nomeUsuario.toString());
                    Double ResultadoVisor = parseDouble(txvVisor.getText().toString());
                    Double Subtrair = ResultadoGravado - ResultadoVisor;

                    SharedPreferences sharedPreferences = getSharedPreferences(MINHAS_PREFERENCIAS,0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nome", Subtrair.toString());

                    editor.commit();
                    txvMensagem.setText("Subtraiu com sucesso");
                }else{
                    SharedPreferences sharedPreferences = getSharedPreferences(MINHAS_PREFERENCIAS,0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nome", txvVisor.getText().toString());
                    editor.commit();
                    txvMensagem.setText("Não havia nada a subtrair, portanto número foi incluído com sucesso");
                }
            }
        });

        botaoMemoriaResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences dados_gravados = getSharedPreferences(MINHAS_PREFERENCIAS,0);

                if (dados_gravados.contains("nome")){
                    String nomeUsuario = dados_gravados.getString("nome", "");

                    if (nomeUsuario.contains(".0")){
                        nomeUsuario = nomeUsuario.replace(".0","");
                        txvVisor.setText(nomeUsuario);
                    }else{
                        txvVisor.setText(nomeUsuario);

                    }
                }
                else{
                    txvMensagem.setText("Sem resultados memorizados");
                }
            }
        });

        botaoMemoriaLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences(MINHAS_PREFERENCIAS,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear().commit();
                txvMensagem.setText("Memória apagada com sucesso");
            }
        });


        botaoLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparTela();

                strVisor = "";
                strOperacao = "";
                strHistorico = "";
                strMensagem = "";
                strProxMovEsperado = "";
                valorA = (double)0;
                valorB = (double)0;
                valorC = (double)0;
                Resultado = (double)0;
                txvVisor.setText("");
                txvHistorico.setText("");
                txvMensagem.setText("");
            }
        });

        botao0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strVisor += "0";
                txvVisor.setText(strVisor);
            }
        });

        botao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strVisor += "1";
                txvVisor.setText(strVisor);
            }
        });

        botao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strVisor += "2";
                txvVisor.setText(strVisor);
            }
        });

        botao3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strVisor += "3";
                txvVisor.setText(strVisor);
            }
        });

        botao4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strVisor += "4";
                txvVisor.setText(strVisor);
            }
        });

        botao5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strVisor += "5";
                txvVisor.setText(strVisor);
            }
        });

        botao6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strVisor += "6";
                txvVisor.setText(strVisor);
            }
        });

        botao7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strVisor += "7";
                txvVisor.setText(strVisor);
            }
        });

        botao8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strVisor += "8";
                txvVisor.setText(strVisor);
            }
        });

        botao9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strVisor += "9";
                txvVisor.setText(strVisor);
            }
        });

        botaoVirgula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (strVisor.contains(".")){
                    strVisor = strVisor;
                    txvVisor.setText(strVisor);
                    //txvMensagem.setText("entrou no if");
                }else{
                    strVisor += ".";
                    txvVisor.setText(strVisor);
                    //txvMensagem.setText("entrou no else");
                }
            }
        });

        botaoSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txvVisor.getText().toString() == ""){

                }else {
                    resultadoNoOperador();

                    // informa a operação, alimenta o valorA com o valor da tela e limpa a tela.
                    strOperacao = "+";
                    if (txvVisor.getText().toString() == ""){

                    }else{
                        valorA = parseDouble(txvVisor.getText().toString());
                        strVisor = "";
                        txvVisor.setText(strVisor);
                        strHistorico = valorA + " + ";
                        txvHistorico.setText(strHistorico);
                    }
                }


            }
        });

        botaoSubtrair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txvVisor.getText().toString() == ""){

                }else {
                    resultadoNoOperador();

                    // informa a operação, alimenta o valorA com o valor da tela e limpa a tela.
                    strOperacao = "-";
                    if (txvVisor.getText().toString() == ""){

                    }else{
                        valorA = parseDouble(txvVisor.getText().toString());
                        strVisor = "";
                        txvVisor.setText(strVisor);
                        strHistorico = valorA + " - ";
                        txvHistorico.setText(strHistorico);
                    }
                }

            }
        });

        botaoMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txvVisor.getText().toString() == ""){

                }else {
                    resultadoNoOperador();

                    // informa a operação, alimenta o valorA com o valor da tela e limpa a tela.
                    strOperacao = "*";
                    if (txvVisor.getText().toString() == ""){

                    }else{
                        valorA = parseDouble(txvVisor.getText().toString());
                        strVisor = "";
                        txvVisor.setText(strVisor);
                        strHistorico = valorA + " + ";
                        txvHistorico.setText(strHistorico);
                    }
                }
            }
        });

        botaoDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txvVisor.getText().toString() == ""){

                }else {
                    resultadoNoOperador();

                    // informa a operação, alimenta o valorA com o valor da tela e limpa a tela.
                    strOperacao = "/";
                    if (txvVisor.getText().toString() == ""){

                    }else{
                        valorA = parseDouble(txvVisor.getText().toString());
                        strVisor = "";
                        txvVisor.setText(strVisor);
                        strHistorico = valorA + " / ";
                        txvHistorico.setText(strHistorico);
                    }
                }
            }
        });

        // está com erro aqui, acho que é problema de tipagem.
        botaoIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txvVisor.getText().toString() == ""){

                }else {
                    // pega o valor do visor
                    valorB = parseDouble(txvVisor.getText().toString());

                    if (porcentagem[0] == true) {
                    }else{
                        strHistorico += valorB;
                    }

                    calculo();
                    valorB = (double)0;
                }
            }
        });

        botaoPorcentagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // informa a operação, alimenta o valorA com o valor da tela e limpa a tela.
                //strOperacao = "+";

                //valorA = parseDouble(txvVisor.getText().toString());
                valorB = parseDouble(txvVisor.getText().toString());
                resultPorcent = (valorA * valorB) / 100;
                strVisor = resultPorcent.toString();

                if (strVisor.contains(".0")){
                    strVisor = strVisor.replace(".0","");
                    txvVisor.setText(strVisor);

                        strHistorico = "";
                        strHistorico = valorB + "% de " + valorA + " = " + strVisor;

                }else{
                    txvVisor.setText(strVisor);

                    strHistorico = valorB + "% de " + valorA + " = " + strVisor;

                }
                strVisor = "";
                txvHistorico.setText(strHistorico);
                porcentagem[0] = true;
            }
        });

        botaoNegativoPositivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (strVisor.contains("-")){
                    strVisor = strVisor.replace("-", "+");
                }else if (strVisor.contains("+")){
                    strVisor = strVisor.replace("+", "-");
                }else{
                    strVisor = "-" + strVisor.toString();
                }
                txvVisor.setText(strVisor.toString());
            }
        });
    }

    public void resultadoNoOperador(){
        if(strOperacao.contains("+")){
            valorB = parseDouble(txvVisor.getText().toString());
            Resultado = valorA + valorB;
            strHistorico = "";
            strHistorico = valorA + " + " + valorB + " = " + Resultado;
            txvHistorico.setText(strHistorico);
            strVisor = "";
            txvVisor.setText(strVisor);


            valorA = Resultado;
            valorB = (double)0;
            strOperacao = "";

        }

        if(strOperacao.contains("-")){
            valorB = parseDouble(txvVisor.getText().toString());
            Resultado = valorA - valorB;
            strHistorico = "";
            strHistorico = valorA + " - " + valorB + " = " + Resultado;
            txvHistorico.setText(strHistorico);
            strVisor = "";
            txvVisor.setText(strVisor);

            valorA = Resultado;
            valorB = (double)0;
            //strOperacao = "";
        }

        if(strOperacao.contains("*")){
            valorB = parseDouble(txvVisor.getText().toString());
            Resultado = valorA * valorB;
            strHistorico = "";
            strHistorico = valorA + " * " + valorB + " = " + Resultado;
            txvHistorico.setText(strHistorico);
            strVisor = "";
            txvVisor.setText(strVisor);

            valorA = Resultado;
            valorB = (double)0;
            //strOperacao = "";
        }

        if(strOperacao.contains("/")){
            valorB = parseDouble(txvVisor.getText().toString());
            Resultado = valorA / valorB;
            strHistorico = "";
            strHistorico = valorA + " / " + valorB + " = " + Resultado;
            txvHistorico.setText(strHistorico);
            strVisor = "";
            txvVisor.setText(strVisor);

            valorA = Resultado;
            valorB = (double)0;
            //strOperacao = "";
        }
    }

    public void calculo(){

        if (porcentagem[0] == true){

            Double resultSomaPorcent = (double)0;

            // Soma
            if (strOperacao == "+"){

                resultSomaPorcent = valorA + resultPorcent;
            }

            // Subtrai
            if (strOperacao == "-"){
                resultSomaPorcent = valorA - resultPorcent;
            }
            strVisor = resultSomaPorcent.toString();

            // Verifica se é inteiro ou não para exibir as casas decimais após a vírgula ou não
            if (strVisor.contains(".0")){
                strVisor = strVisor.replace(".0","");
                txvVisor.setText(strVisor);

                    strHistorico += "   " + valorA + " " + strOperacao + " " + resultPorcent + " = " + strVisor;

            }else{
                txvVisor.setText(strVisor);

                strHistorico += "   " + valorA + " " + strOperacao + " " + resultPorcent + " = " + strVisor;

            }

            txvHistorico.setText(strHistorico);
            strHistorico = "";

            valorA = (double)0;
            valorB = (double)0;
            resultPorcent = (double)0;
            porcentagem[0] = false;
            strOperacao = "";

        }else{
            // Soma
            if (strOperacao == "+"){
                Resultado = valorA + valorB;
            }

            // Subtrai
            if (strOperacao == "-"){
                Resultado = valorA - valorB;
            }

            // Divide
            if (strOperacao == "/"){
                Resultado = valorA / valorB;
            }

            // Multiplica
            if (strOperacao == "*"){
                Resultado = valorA * valorB;
            }

            // Multiplica
            if (strOperacao == "%"){
                Resultado = (valorA*100) / valorB;
            }

            strVisor = Resultado.toString();
            Resultado = (double)0;

            if (strVisor.contains(".0")){
                strVisor = strVisor.replace(".0","");
                txvVisor.setText(strVisor);
                if(valorB.toString() == "0.0"){

                }else{
                    strHistorico = "";
                    strHistorico = valorA + " " + strOperacao + " " + valorB + " = " + strVisor;
                }
            }else{
                txvVisor.setText(strVisor);
                if(valorB.toString() == "0.0"){

                }else{
                    strHistorico = "";
                    strHistorico = valorA + " " + strOperacao + " " + valorB + " = " + strVisor;
                }
            }
            txvHistorico.setText(strHistorico);
            strOperacao = "";
        }

    }

    public void limparTela(){
        txvVisor.setText("");
    }

    public void  Somar(){
        valorA = Double.valueOf(strVisor); // 1
        Resultado = valorA + valorC;
        txvVisor.setText(Resultado.toString());
    }

}

/*FALTANDO FAZER:
- TRATAR COM CONTAINS SE CLICAR VÁRIAS VEZES NO OPERADOR PARA NÃO BUGAR O APP
- TRATAR PARA DAR RESULTADO SÓ CLICANDO NOS OPERADORES, SEM CLICAR NO IQUAL
- EXIBIR VIRGULA E GRAVAR PONTO*/


