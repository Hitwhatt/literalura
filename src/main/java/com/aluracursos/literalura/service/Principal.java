package com.aluracursos.literalura.service;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LivroRepository;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        int opcao = -1;
        while (opcao != 0) {
            String menu = """
                    \n========== LITERALURA ==========
                    1 - Buscar livro por título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em determinado ano
                    5 - Listar livros em determinado idioma
                    0 - Sair
                    =================================
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> buscarLivroPorTitulo();
                case 2 -> listarLivrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosNoAno();
                case 5 -> listarLivrosPorIdioma();
                case 0 -> System.out.println("Encerrando o LiterAlura. Até logo!");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarLivroPorTitulo() {
        System.out.println("Digite o título do livro que deseja buscar:");
        String titulo = leitura.nextLine();
        String json = consumo.obterDados(ENDERECO + titulo.replace(" ", "+"));
        DadosResposta resposta = conversor.obterDados(json, DadosResposta.class);

        if (resposta.resultados().isEmpty()) {
            System.out.println("Livro não encontrado!");
            return;
        }

        DadosLivro dadosLivro = resposta.resultados().get(0);
        Livro livro = new Livro();
        livro.setTitulo(dadosLivro.titulo());
        livro.setIdioma(dadosLivro.idiomas().get(0));
        livro.setNumeroDownloads(dadosLivro.numeroDownloads());

        if (!dadosLivro.autores().isEmpty()) {
            DadosAutor dadosAutor = dadosLivro.autores().get(0);
            Autor autor = new Autor(dadosAutor.nome(), dadosAutor.anoNascimento(), dadosAutor.anoFalecimento());
            autor.setLivro(livro);
            livro.setAutores(List.of(autor));
        }

        try {
            livroRepository.save(livro);
            System.out.println("\nLivro salvo com sucesso!");
            System.out.println(livro);
        } catch (Exception e) {
            System.out.println("Livro já cadastrado no banco de dados!");
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado ainda!");
            return;
        }
        livros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado ainda!");
            return;
        }
        autores.forEach(a -> System.out.println(a + "\n---------------------------"));
    }

    private void listarAutoresVivosNoAno() {
        System.out.println("Digite o ano para verificar autores vivos:");
        int ano = leitura.nextInt();
        leitura.nextLine();
        List<Autor> autores = autorRepository.findAutoresVivosNoAno(ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado para o ano " + ano);
            return;
        }
        autores.forEach(a -> System.out.println(a + "\n---------------------------"));
    }

    private void listarLivrosPorIdioma() {
        String menu = """
                Digite o idioma desejado:
                en - Inglês
                pt - Português
                es - Espanhol
                fr - Francês
                """;
        System.out.println(menu);
        String idioma = leitura.nextLine();
        List<Livro> livros = livroRepository.findByIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado nesse idioma!");
            return;
        }
        livros.forEach(System.out::println);
    }
}