# encoding: utf-8

module Deepspace
class SpaceCity < SpaceStation
    attr_reader :base, :collaborators

    def initialize base, rest # base: SpaceStation, rest: Array<SpaceStation>
        # FIXME I have no idea what I'm doing
        super
        @collaborators
        @base
    end

    def fire

    end

    def protection

    end

    def setLoot (loot)

    end
end
end