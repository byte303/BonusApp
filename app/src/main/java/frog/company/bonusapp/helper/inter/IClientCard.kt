package frog.company.bonusapp.helper.inter

import frog.company.bonusapp.model.Card

interface IClientCard {
    fun onSelectCard(card : Card)
}