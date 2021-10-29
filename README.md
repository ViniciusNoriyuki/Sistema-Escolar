# Sistema-Escolar

API Rest desenvolvida em linguagem Java, com utilização de Autenticação e Autorização com Tokens JWT e sistema de envio de email com SMTP do Google.

Funcionalidades:

- Há professores, alunos e disciplinas pré-cadastrados na base de dados (h2-console) para uso. Também possível visualizar em /services/DBService para ver suas senhas.
- Profile de Teste com banco de dados em memória (H2) e também profile de Desenvolvimento com banco de dados MySQL
- Retorno de erros personalizados
- Atribuido automaticamente perfil de ADMIN ao criar Professores e USER ao criar Alunos
- CRUD de disciplinas 
  - Cada disciplina possui seu professor e vários alunos
  - Apenas professores podem criar, alterar, deletar e atribuir disciplinas a alunos e professores
- CRUD de alunos
  - Cada aluno possui suas notas e disciplinas 
  - Alunos visualizam apenas suas próprias notas e disciplinas
  - Apenas professores podem criar, alterar e deletar alunos
- CRUD de professores 
  - Cada professor possui sua disciplina
  - Apenas professores podem criar, alterar e deletar professores
- CRUD de notas (apenas professores)
