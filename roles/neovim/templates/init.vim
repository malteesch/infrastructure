set tabstop=4
set noshowmode
set number
set relativenumber

call plug#begin(stdpath('data') . '/plugged')
Plug 'itchyny/lightline.vim'
Plug 'udalov/kotlin-vim'
Plug 'doums/darcula'
Plug 'joshdick/onedark.vim'
Plug 'pearofducks/ansible-vim'
Plug 'nvim-lua/plenary.nvim'
Plug 'nvim-telescope/telescope.nvim'
Plug 'nvim-treesitter/nvim-treesitter', {'do': ':TSUpdate'}  " We recommend updating the parsers on update
call plug#end()

syntax on
colorscheme darcula

nnoremap <C-n> :Telescope find_files<CR>

" INSERT mode
inoremap jk <Esc>
inoremap jj <Esc>
inoremap kj <Esc>
inoremap kk <Esc>
inoremap <Esc> <Nop>
